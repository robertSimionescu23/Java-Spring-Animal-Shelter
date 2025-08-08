# Core features for Animal Shelter web app

1. ## Have an Database containing
	- It should contain animal data:
		- Id
		- Name   (Easier for users to use it for searching a specific animal)
		- Age    (Optional, can use it for management)
		- Species( Let's assume it's an all animal shelter)
		- It should contain data about the adoption process
	- It should contain data about the adoption(if it occurred)
		- Id
		- Stage (pending -> done -> cancelled) -> Cancelled should delete the Adoption Entry
		- Date  (should be updated per stage)
		- Adopter Name (It should not be accessible to the public)
		- Animal Id (For linking purposes)
	- It should handle animal pictures
		- Because an animal may have more than one picture, a separate JPA model should be used
		- Id
		- filename (for tracking purposes.)
		- the picture
		- Link to an animal
	- This is managed
2. ## Showcase Animals based on categories (More traits may be added)

3.  ## Have a system that allows authorized persons to upload images, handle animal data, but not users
    - Different table containing encoded passwords and usernames
    - Access login page with special url (So regular users can't stumble naturally on the login)

4. ## Schedule visits for animals, schedule adoption meetings
	- Calendar for meetings and adoptions
	- Allocate 1 hour or more for visits
	- Block visits if there is an adoption for that animal scheduled
