# racing

### Description

Application works with Entities, such as Team, Pilot, Report.
Communication is build as microservices, 
but now in one project for easier setup and debug.

### Architecture
Controller -> MessagePublisher ->
RabbitMQ -> MessageReceive ->
Repository -> Database

# How it works

Go to [http://localhost:8080/init]() it async process
creating events about inserting fake team, pilots and reports.

While the receiver subscribes the RabbitMQ and get events and saves all the
entities to h2 with some delay, the controller redirects to `/data`, 
where the js script goes to `/update` to get teams directly from databases.

That is why, we can see how the database updates and the pages updates.