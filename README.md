# racing

### Description

Application works with Entities, such as Team, Pilot, Report.
Communication is build as microservices, 
but now in one project for easier setup and debug.

### Architecture
Controller -> Service -> MessagePublisher ->
RabbitMQ -> MessageReceive ->
Repository -> Database