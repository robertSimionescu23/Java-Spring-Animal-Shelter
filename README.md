# Animal Shelter Web project

This is a web app created to manage a Animal Shelter and digitalize it's workings.
It aims to provide a smooth user experience, but also easy management and monitoring for the administrators.

# Technologies used
**Back End**  - Java Spring Boot

**Data Base** - Postgres Instance running on Docker Container

**Front End** - React.tsx + TailWindCSS


# Core features for Animal Shelter web app

1. ## Have a Database containing
	- It should contain animal data:
		- Id
		- Name   (Easier for users to use it for searching a specific animal)
		- Age    (Optional, can use it for management)
		- Species( Let's assume it's an all animal shelter)
		- It should contain data about the adoption process
    - More animal data will be added later on (Vaccines, Traits, etc)
	- It should contain data about the adoption(if it occurred)
		- Id
		- Stage (pending -> done -> cancelled) -> Cancelled should delete the Adoption Entry
		- Date  (should be updated per stage)
		- Adopter Name (It should not be accessible to the public)
		- Animal Id (For linking purposes)
	- This is modeled in the PostgresSQL database  running in the docker container using JPA


2. ## Showcase Animals based on categories (More traits may be added)
    - For example, offer category for senior animals, or younger animals.
    - Enable searching for certain ages of animals, species etc.

3.  ## Have a system that allows authorized persons to upload images, handle animal data, but not users
    - Different table containing encoded passwords and usernames
    - Access login page with special url (So regular users can't stumble naturally on the login)

4. ## Handle image upload on an per animal basis, by authorized personnel only

5. ## Schedule visits for animals, schedule adoption meetings
	- Calendar for meetings and adoptions
	- Allocate 1 hour or more for visits
	- Block visits if there is an adoption for that animal scheduled
