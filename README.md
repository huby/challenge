**BCP Code Challenge**

1. Creating the docker file from source

1.1. Clone the source code from github

`clone https://github.com/huby/challenge.git`

1.2. Build the project with the following command in the terminal or gitbash (windows)

`./gradlew build`

1.3. Create the docker image with the next command:

`docker build -t hhuby/bcp-challenge . `

1.4. Then run the container:

`docker run -p 8080:8080 -t hhuby/bcp-challenge`

4. Run docker container from docker hub image

`docker run -p 8080:8080 -it hhuby/bcp-challenge`

3. For testing import the file bcp.postman_collection.json in Postman.