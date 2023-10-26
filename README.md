## BANTADS

### Running locally

1. First, you need to build the images
```bash
docker compose build
```
2. Then, you can start the services
```bash
docker compose up -d
```


### API Gateway
Pasta do front
1. Instalando json-server
```
npm install -g json-server
```

2. Instalando os módulos do pacote do API Gateway
```
npm install http express morgan helmet express-http-proxy cookie-parser body-parser jsonwebtoken dotenv-safe
```

3. Executando JSON
```
json-server --no-cors --port 5000 --watch server/cliente/db.json
json-server --no-cors --port 5001 --watch server/conta/db.json
json-server --no-cors --port 5002 --watch server/auth/db.json
json-server --no-cors --port 5003 --watch server/gerente/db.json
```

4. Executando o node
```
node index.js
```