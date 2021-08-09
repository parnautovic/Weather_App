# Weather_App

The user logs in to the system and receives a user token with which he is further authorized
during each address to the system. Can view notification lists provided by the system
offers and subscribes to them.
Notifications can be: weather information, flights from certain
airport, exchange rate list, etc. When a user subscribes to a particular list, the system will
periodically (depending on the type of subscription), send information of interest via email.

Service 1 - functionalities:
- User registration and login
- View and store user information in a database (optional)
- View information about the types of notifications that the system offers as well
user subscriptions to notification types
- Ability of the user to subscribe / unsubscribe to a specific notification list
(note: the user chooses which list to subscribe to and which time type
wants notifications, if more than one possible type is supported (hourly,
daily, weekly ...))
- Withdraw data from service 2 and forward to the sending service
email via message broker

- Service 2 - functionalities:
- Integration to an open app (minimum one) to retrieve information
of interest
- Periodic withdrawal of data from a remote service and storage in
local database
- Open APIs to view the offered data to which it integrates
Service 1

- Service 3 - functionalities:

- Authorization to mail service and sending e-mail from the application
- Subscribe to a message broker (RabbitMQ, Kafka, ActiveMQ ...) and
listening for messages to be sent. It is not necessary to implement templates and
preparation of stylized content of the e-mail to be sent. Just with
brokers pick up information about the address to which mail is sent and the text that goes to
the message itself.
