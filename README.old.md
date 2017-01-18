# Giddy Core

Routing Library using Graphhopper

## IDE Setup

1. Get the styleguides from here for your ide: [Google Style Guides](https://github.com/google/styleguide)
2. Install them.

For IntelliJ:

0. [Download](https://raw.githubusercontent.com/google/styleguide/gh-pages/intellij-java-google-style.xml)
1. Preferences -> Editor -> Code Style -> Manage
2. Import 'IntelliJ IDEA Code Style XML'
3. After importing select it from the list -> press 'Copy to project'

## Gitlab CI locally

    docker-machine start
    eval $(docker-machine env default)
    docker run -d --name gitlab-runner --restart always \
               -v /srv/gitlab-runner/config:/etc/gitlab-runner \
               gitlab/gitlab-runner:latest
    gitlab-ci-multi-runner exec docker build


## Build

1. Install [Gradle](https://gradle.org/gradle-download/).
2. `./gradlew -q downloadMap` to download the pbf file.
3. `/gradlew build` to create the jar.


## Deploy

1. Deploy env was configured using this tutorial [DigitalOcean](https://www.digitalocean.com/community/tutorials/how-to-use-git-hooks-to-automate-development-and-deployment-tasks). NGINX was used for proxy `nano /etc/nginx/sites-enabled/default` to see the configuration.

    `
    #One time only
    git remote add production deployer@139.59.154.14:giddy-core
    #When you want to deploy
    git push production master
    `

2. `ssh root@139.59.154.14`
3. `cd /home/deployer/giddy-core-production`
4. `gradle assemble`
5. `service giddy-core restart`
6. (Optional) `nano /etc/systemd/system/giddy-core.service` Change the executable jar location.

[About Installing Spring Boot applications](http://docs.spring.io/spring-boot/docs/current/reference/html/deployment-install.html)

#TODO This needs to happen automatically

## Usage

TODO: Write usage instructions


## JSONDoc

You can go to http://localhost:8080/jsondoc-ui.html, insert http://localhost:8080/jsondoc in the box and get the documentation.

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

TODO: Write history

## Credits

TODO: Write credits

## License

TODO: Write license
