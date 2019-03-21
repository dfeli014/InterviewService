# InterviewService
Interview API for Staging Management System for Revature.


#Step 1 - Set up DB and Interview Service
1. You will need to create your own PostgreSQL DB on either your local machine or on a RDS. 
2. Figure out the schema necessary for this service. 
3. Will need to run the scripts included on that schema. 
4. All environment variables will need to be set. 
  "DEPLOYMENT_STAGE": "dev",
  "COGNITO_KEY": "Key from Cognito",
  "COGNITO_URL": "URL for Cognito",
  "DB_URL": "The actual DB URL",
  "DB_USERNAME": "DB username",
  "DB_PASSWORD": "DB password",
  "DB_SCHEMA": "Schema where you have the tables saved", 
  "GATEWAY_URL": "Gateway or Discovery Service URL (i.e. localhost:port")
5. Start running the Gateway Service and the Service Registry 
6. Start up this microservice
7. (Optional) You will probably need to start up the User Service to get all of the Cohorts and Associates for New Interview Component on      the UI

#Step 2 - Set up UI 


Bugs
  Feign calls need to be done after pagination to optimize and speed up calls.
  
