# voyager-backend

## Requirements

- docker-compose

## Getting Started

```shell
# clone giithub repo, including submodules
git clone --recurse-submodules https://github.com/somamvp/voyager-backend.git

cd voyager-backend

# Need to docker-compose up first to create the database
docker-compose up --build

# Dump the database and load the data
mysqldump  --single-transaction --databases local-db -u root -p > local-db.sql
mysql -u root -p < local-db.sql
```
