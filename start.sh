# Build Docker image
docker build -t tracetogether .

# Run Docker container
docker run -p 8080:8080 tracetogether