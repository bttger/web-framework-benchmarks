package main

import (
	"fmt"
	"time"

	"github.com/gin-gonic/gin"
)

type uri struct {
	UserID int `uri:"userId" binding:"required"`
	Offset int `uri:"offset" binding:"required"`
}

type query struct {
	Model  string `form:"model" binding:"required"`
	Factor string `form:"factor" binding:"required"`
	Length int    `form:"length" binding:"required"`
	Width  int    `form:"width" binding:"required"`
	Allow  bool   `form:"allow" binding:"required"`
}

type header struct {
	APIKey    string `header:"x-api-key" binding:"required"`
	SessionID string `header:"x-session-id" binding:"required"`
}

type insertObject struct {
	Name      string    `json:"name" binding:"required"`
	Addresses []address `json:"addresses" binding:"required"`
	OldTown   bool      `json:"oldTown" binding:"required"`
}

type address struct {
	Street string `json:"street" binding:"required"`
	Number int    `json:"number" binding:"required"`
	City   string `json:"city" binding:"required"`
}

type insertObjectResponse struct {
	insertObject        // anonymous field, adds all insertObject-struct's fields to the new struct
	ID           int    `json:"id"`
	CreatedAt    string `json:"createdAt"`
}

func fib(number int) int {
	if number <= 1 {
		return 1
	}
	return fib(number-1) + fib(number-2)
}

func main() {
	gin.SetMode(gin.ReleaseMode)

	router := gin.New()
	router.Use(gin.Recovery())

	/*
	   getSerialized
	*/
	router.GET("/serialize", func(c *gin.Context) {
		c.JSON(200, gin.H{"message": "Hello, World!"})
	})

	/*
	   getSerializedBig
	*/
	router.GET("/serialize/big", func(c *gin.Context) {
		publications := make([]gin.H, 50)
		for i := 0; i < 50; i++ {
			publications[i] = gin.H{
				"year":        1821 + i,
				"related":     true,
				"description": fmt.Sprint("Some discovery in ", 1821+i),
			}
		}

		c.JSON(200, gin.H{
			"family": "Elephantidae",
			"scientificClassification": gin.H{
				"kingdom":     "Animalia",
				"phylum":      "Chordata",
				"class":       "Mammalia",
				"order":       "Proboscidea",
				"superfamily": "Elephantoidea",
				"classifier": gin.H{
					"name": "John Edward Gray",
					"born": gin.H{
						"year":    1800,
						"month":   "February",
						"day":     12,
						"city":    "Walsall",
						"country": "England",
					},
					"died": gin.H{
						"year":    1875,
						"month":   "March",
						"day":     7,
						"city":    "London",
						"country": "England",
					},
					"publications": publications,
				},
			},
		})
	})

	/*
	   getPlainText
	*/
	router.GET("/plain/text", func(c *gin.Context) {
		c.String(200, "%s", "Hello, World!")
	})

	/*
	   getQueryResult
	*/
	router.GET("/query/:userId/tools/:offset", func(c *gin.Context) {
		var uriModel uri
		if err := c.ShouldBindUri(&uriModel); err != nil {
			c.JSON(400, gin.H{"message": err})
			return
		}

		var queryModel query
		if err := c.ShouldBindQuery(&queryModel); err != nil {
			c.JSON(400, gin.H{"message": err})
			return
		}

		var headerModel header
		if err := c.ShouldBindHeader(&headerModel); err != nil {
			c.JSON(400, gin.H{"message": err})
			return
		}

		c.JSON(200, gin.H{
			"id":      6000,
			"foundAt": time.Now().Format(time.RFC3339),
		})
	})

	/*
	   insertObject
	*/
	router.POST("/insert", func(c *gin.Context) {
		var insertObjectModel insertObject
		if err := c.ShouldBindJSON(&insertObjectModel); err != nil {
			c.JSON(400, gin.H{"message": err})
			return
		}

		fmt.Println(insertObjectModel)

		resp := insertObjectResponse{
			insertObject: insertObjectModel,
			ID:           300,
			CreatedAt:    time.Now().Format(time.RFC3339),
		}
		c.JSON(201, resp)
	})

	/*
	   getCalculated
	*/
	router.GET("/calculate", func(c *gin.Context) {
		c.JSON(200, gin.H{"fibonacci": fib(27)})
	})

	router.Run()
}
