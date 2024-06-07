# Use the official Python image from the Docker Hub
FROM python:3.9-slim

# Set the working directory
WORKDIR /app

# Copy the static files to the working directory
COPY ./src /app/public

# Expose port 8000
EXPOSE 8000

# Start the Python HTTP server when the container starts
CMD ["python3", "-m", "http.server", "8000", "--directory", "public"]
