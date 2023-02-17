FROM node:18.12.1
COPY package*.json ./
RUN mkdir ./OPENVIDU_SERVER/
COPY . ./OPENVIDU_SERVER/
RUN npm install

EXPOSE 5000
