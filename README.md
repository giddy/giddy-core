# giddy-core


### Local Setup

1. Add to **/etc/environment** or **~/.bashrc** or **~/.zshrc**:
    `
    export GIDDY_POSTGRES_DB_HOST="localhost"
    export GIDDY_POSTGRES_DB_USERNAME=""
    export GIDDY_POSTGRES_DB_PASSWORD=""
    `
2. `source ~/.zshrc`
3. `exec zsh`

### Prodcution Setup

1. Add to **/etc/environment** or **~/.bashrc** or **~/.zshrc**:
    `
    export GIDDY_POSTGRES_DB_HOST=[replace with value]
    export GIDDY_POSTGRES_DB_USERNAME=[replace with value]
    export GIDDY_POSTGRES_DB_PASSWORD=[replace with value]
    export AWS_ACCESS_KEY_ID=[replace with value]
    export AWS_SECRET_ACCESS_KEY=[replace with value]
    export AWS_REGION=[replace with value]
    export S3_BUCKET_NAME=[replace with value]
    `
2. `source ~/.zshrc`
3. `exec zsh`