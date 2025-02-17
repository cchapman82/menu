Author:  Christopher Chapman
StartDate: April 25, 2023

	Info

	to Run:
		java -cp target/menu-app-1.0.jar:.:postgresql-42.7.0.jar com.menu.app.Main


	A program to input items into a menu and be able to make printouts of the items in categories.
		
		functions -- of program
			enterItem -- from user
			printRestaurant
			printMenu
				restaurant
					category
						name
						description
						price
			printOneCategory

		objectManagment
			-- holds the array lists that hold the data for all other program actions
			-- want it to be a Singleton
			-- writes and reads to file to populate lists
				-- later to be reliant upon database
			Lists
				restaruants
				menuItems
				ingredients
				
				listMethods
					add
					delete
					update
		restaurant
			name
			address
			phoneNumber
			description	
			chef
			generalManager
			asstManager
			barManager

		menuItem

			constructor
				name
				restaurant
				menuCategory
				description
				price
				ingredients 
				preparationStyle
				size
				
				constructorMethods
					getAllergies
					setAllergies
				
			getters&setters
				getName
				getRestaurant
				getMenuCategory
				getDescription
				getPrice
				getIngredients
				getAllergies
				getPreparationStyle
				getSize
				setName
				setRestaurant
				setMenuCategory
				setDescription
				setPrice
				setIngredients
				setAllergies
				setPreparationStyle
				setSize

			methods				
				allergies --methodGetFromIngredients

			menuCategory
				appetizer
				priliminaryEntree
				entree
				side
				dessert
				beverage
					alcoholic
						wine -- primaryObj
							glass --serving size
							caraf -- 
							bottle --
							sparking
							flat
						beer -- primaryObj
						cider -- primaryObj
						spirit -- primaryObj
						cocktails
					
					nonAlcoholic
						soda -- primaryObj
						mockTail
						juice -- primaryObj
						coffee -- primaryObj
						tea -- primaryObj
							hot
							iced
						kamboocha -- primaryObj

		ingredient -- primaryObj
			description
			cost
			preparationStyle
			allergies
			region
			healthStats???????

		allergy -- primaryObj
			description
			tx??????????????
		
		people  -- maybe come from another database that is broader than this program
			name
			relationship
			role
			address -- unless needed, co address
			phoneNumber		

	Interfaces/Factory
		interface to make an object
			what object to make and add to certain List, if needed, only in certain	settings	
		ObjectMaker  -- in the file that holds the object/  
				from contrlor
			holds one of each object in program and runs implimenter
			to make and store the object in its repective list
				needs to have a method to ask for each object needs
				returns the object needed based on the parameter
				

			


	-------------------------  later maybe add inventory capabilities based on receipie

		recipeie
			ingredient    	amount  --tuple/pair
			instructions
