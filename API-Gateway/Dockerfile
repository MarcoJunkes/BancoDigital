FROM node:16.20.2
WORKDIR /app
COPY package*.json ./
RUN npm install http express morgan helmet express-http-proxy cookie-parser body-parser jsonwebtoken dotenv-safe
COPY --chown=node:node . .
EXPOSE 3000
CMD [ "node", "index.js" ]
