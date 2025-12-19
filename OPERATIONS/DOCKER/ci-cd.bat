CD ..\..
docker build -f OPERATIONS/DOCKER/Dockerfile -t javierlete/mi-react:0 .
REM docker run -dp 80:80 javierlete/mi-react:0
REM START http://localhost
docker push javierlete/mi-react:0
