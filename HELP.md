# Date Extractor

Here is a simple web application which aims to extract the date information from the input text. 

## Description 
This project includes 2 pages.
In the first page, you can input the text, and submit.
In the second page, you will see a table which is a date list, based on your input content, including 4 information : year, month, day and times.
The project supports different format of date, in English or in numeric version, also in French.

## How to run the project
### manual build in local
If your download this project directly, you need to build the project by yourself.
* Step 1, import project into your IDE (Java 8, Maven project)
* Step 2, use `mvn package docker:build` to build docker image
* Step 3, use `docker run -p 8080:8080 -t springboot/demo` to run your image in docker

### using downloaded docker image 
* You can download the docker image by the following command 
  
* Step 1, `docker pull ghcr.io/salutpp1177/dateextracteproject:master`
* Step 2, use `docker images` to check IMAGE_ID of the name <b>ghcr.io/salutpp1177/dateextracteproject </b>
* Step 3, run project with the following command

`docker run -p 8080:8080 -t IMAGE_ID`

After running the project, you can locate into <b>localhost:8080</b> in your browser.
