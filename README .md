
# Desafio-Backend-Itau

The project is a REST API designed to manage financial transactions and calculate real-time statistics based on those transactions. It follows a set of requirements typical of Itaú technical challenges, such as those found in other similar repositories https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior?tab=readme-ov-file. The application was built with a focus on code quality, organization, error handling and documentation, aspects usually assessed in technical selection processes.

The repository contains the standard structure of a Spring Boot project, including configuration files (such as pom.xml for Maven), Java source code, and a README with basic instructions for running and understanding the project.


## API Reference

### Prerequisites:
Java JDK installed (version compatible with the project, usually 17)

Maven installed to manage dependencies and run the build.

Use tools like Postman or Insomnia to interact with the endpoints.

#### Create transaction

```http
  POST /transactions/create
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `value` | `double` | **Required**. Value for transaction|
| `dataHora` | `OffsetTime` | **Required**. OffsetTime for transaction|

#### Get transactions 60 seconds

```http
  GET /statistics/60
```

| Description                       |
|  :-------------------------------- |
|  **Required**. Returns transactions whitin 60 seconds.  |

#### Get transactions 120 seconds

```http
  GET /statistics/120
```

| Description                       |
|  :-------------------------------- |
|  **Required**. Returns transactions whitin 120 seconds.  |


## Installation
Clone the repository
```bash
  git clone https://github.com/ThiagoST32/Desafio-Itau-Spring-Boot.git
```
Access the project directory
```bash
  cd Desafio-Itau-Spring-Boot
```
Install dependecies
```bash
  mvn install
```
Starts API
```bash
  mvn spring-boot:run
```
The API will be available at
```bash
  http://localhost:8080.
```

# Install Docker in Linux

## Quick Guide: Running the Project with Docker on Ubuntu

This guide shows how to use Docker on Ubuntu to run the project.

## Prerequisites
- **Docker** installed on Ubuntu.
- Project source code cloned from the repository.

## Steps

### 1. Install Docker
- Open a terminal and update the package list:
  ```bash
  sudo apt-get update
    ```
     ```bash
  sudo apt install -y docker.io
    ```
     ```bash
    sudo systemctl start docker
    sudo systemctl enable docker
    ```
     ```bash
    docker --version
    ```

# Install in Windows

## Quick Guide: Running the Project with Docker on Windows

This guide shows how to use Docker on Windows to run the project.

## Prerequisites
- **Docker Desktop** installed (download from [docker.com](https://www.docker.com/products/docker-desktop/)).
- Project source code cloned from the repository.

## Steps

### 1. Install Docker Desktop
- Download and install Docker Desktop for Windows.
- Open Docker Desktop to ensure it’s running (check the system tray for the Docker icon).


# Tutorial: Importing a Collection of Endpoints into Postman

This tutorial explains how to import a collection of endpoints into Postman to test the project's API. The collection includes the endpoints described in the documentation, such as `POST /transacoes`, `GET /estatisticas`, and `DELETE /transacoes`.

## Prerequisites
- **Postman** installed on your computer (download from [postman.com](https://www.postman.com/downloads/)).
- A JSON file of the endpoint collection (e.g., `DesafioItauCollection.json`), which may be provided in the repository or generated manually.

## Step-by-Step

### 1. Obtain the Collection File
- Locate the JSON collection file in the repository (e.g., in the `docs/` folder or the project root).
- If the file is not available, ask the developer or create a collection manually in Postman and export it (see the "Creating and Exporting a Collection" section below).

### 2. Open Postman
- Launch Postman on your computer.

### 3. Access the Import Option
- In the top left corner, click the **"Import"** button.
  - This will open a window for importing collections or files.

### 4. Import the JSON File
- In the import window:
  1. Select the **"File"** tab.
  2. Click **"Choose Files"** or drag the `DesafioItauCollection.json` file into the designated area.
  3. Click **"Import"** to confirm.
- Postman will load the collection, and it will appear in the left sidebar under the name defined in the file (e.g., "Desafio Itaú API").

### 5. Configure the Environment (Optional)
- If the collection uses variables (e.g., `{{baseUrl}}` for `http://localhost:8080`), set up an environment:
  1. Click the gear icon in the top right corner and select **"Manage Environments"**.
  2. Create a new environment (e.g., "Local"):
     - Name: `Local`
     - Variable: `baseUrl`, Value: `http://localhost:8080`
  3. Save and select the environment from the dropdown menu in the top right corner.

### 6. Test the Endpoints
- Open the collection in the left sidebar.
- Click each endpoint (e.g., `POST /transacoes`) to view its details:
  - HTTP method, URL, request body (if applicable), and headers.
- Click **"Send"** to test each request.
- Check the responses in the bottom panel (e.g., status `201 Created` for `POST /transacoes`).

## Usage Example
- **Endpoint**: `POST /transacoes`
  - **URL**: `localhost:8080/transacoes/create`
  - **Body (JSON)**:
    ```json
    {
      "valor": 100.50,
      "dataHora": "2025-04-02T10:30:00-03:00"
    }
## Deployment with Docker

```bash
  cd Desafio-Itau-Spring-Boot
```
Build image from Dockerfile
```bash
  docker build -t *name_image* .
```
Here you are initizaling the container with port 8080:8080
```bash
  docker run -p "8080:8080" *your_name_image*
```
Use anyone app for test endpoints (Postman, Insomnia)
```bash
  A API estará disponível em http://localhost:8080.
```

## Deployment with Docker-Compose
In terminal
```bash
  cd Desafio-Itau-Spring-Boot
```
Up application
```bash
  docker-compose up -d
```
To see application running
```bash
  docker ps -a
```


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logoColor=white)](https://github.com/ThiagoST32)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/)


