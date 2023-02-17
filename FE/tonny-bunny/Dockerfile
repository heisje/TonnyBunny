FROM node:18.12.1 as build-stage
WORKDIR /var/jenkins_home/workspace/tonnybunny/FE/tonny-bunny
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
FROM nginx:stable-alpine as production-stage

COPY --from=build-stage /var/jenkins_home/workspace/tonnybunny/FE/tonny-bunny/dist /usr/share/nginx/html
COPY --from=build-stage /var/jenkins_home/workspace/tonnybunny/FE/tonny-bunny/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 5080
CMD ["nginx", "-g","daemon off;"]
