# Thy Flight Reservation

Thy Flight Reservation is a web application for booking and managing flight reservations. This project is built using Java, Java Spring, Thymeleaf for templating, PostgreSQL, and incorporates JWT (JSON Web Token) security for authentication. Additionally, it utilizes the AirLabs API for retrieving external data related to flights.

## Features

- User authentication and authorization using JWT security.
- Flight search and booking functionality.
- Integration with the AirLabs API for external flight data.
- Thymeleaf templating for dynamic and responsive user interfaces.
- Manage and view flight reservations.

## Technologies Used

- **Java**: The primary programming language used for backend development.
- **Java Spring**: A powerful and comprehensive framework for building Java-based enterprise applications.
- **JWT Security**: JSON Web Token is employed for secure user authentication.
- **Thymeleaf**: A modern server-side Java template engine for web and standalone environments.
- **PostgreSQL**: A robust open-source relational database system.
- **AirLabs API**: An external API used for retrieving additional data related to flights.

## Getting Started

These instructions will help you set up the project on your local machine for development and testing purposes.

### Prerequisites

- Java 8 or later
- PostgreSQL
- Maven

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/wiitcha/thy-flight-reservation.git
    ```

2. Navigate to the project directory:

    ```bash
    cd thy-flight-reservation
    ```

3. Set up PostgreSQL and update the database configuration in `application.properties`.

4. Obtain API credentials for AirLabs and update the configuration in the application.

5. Build the project using Maven:

    ```bash
    mvn clean install
    ```

6. Run the application:

    ```bash
    java -jar target/thy-flight-reservation.jar
    ```

7. Access the application at `http://localhost:8080/` in your web browser.

### Usage

- Utilize the JWT-secured authentication for user login and access to protected routes.
- Leverage the AirLabs API for additional external flight data.
- Enjoy the dynamic and responsive user interfaces powered by Thymeleaf templating.
- Use the flight search and booking functionality on the main application page.

## Contributing

Contributions are welcome! Feel free to open issues or pull requests.

## Acknowledgments

- Thanks to the Java and Spring communities for providing excellent tools and frameworks.
- Special thanks to AirLabs for providing external flight data through their API.
- Thymeleaf for enabling dynamic and responsive templating in the application.
