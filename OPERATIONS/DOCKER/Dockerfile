# CI: CONTINUOUS INTEGRATION

# Imagen de node 24 (LTS) basada en Linux Alpine
# La nombramos como builder para que el siguiente paso pueda hacer referencia a esta
FROM node:24-alpine AS builder

# Cambiar de directorio actual dentro del contenedor a /app
WORKDIR /app

# Herramientas posiblemente necesarias para algunos build de node
# RUN apk add --no-cache python3 make g++

# Copiar los ficheros al directorio /app del contenedor
COPY FRONTEND/react-builder/package*.json ./

# Generación de node_modules (clean install)
RUN npm ci

# Copia del resto del proyecto de la carpeta del equipo al contenedor
COPY FRONTEND/react-builder/ .

# Construcción del proyecto para producción
RUN npm run build

# CD: CONTINUOUS DEPLOYMENT

# Imagen de NginX (Servidor web ligero y rápido) sobre Alpine Linux
FROM nginx:alpine

# Copiamos el build al directorio público de Nginx
COPY --from=builder /app/build /usr/share/nginx/html

# Exponemos el puerto 80
EXPOSE 80
