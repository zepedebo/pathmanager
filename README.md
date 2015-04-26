# pathmanager

Some day this will be a utility for managing Pathfinder gaming sessions. Right now it is an assignment for school.

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

Before you run the first time, you need to create a database, do this by running
    lien ragtime migrate

To build the Clojurescript run
    lein cljsbuild once

To start a web server for the application, run:

    lein ring server

This will also build the Clojure part of the application.

## License

Copyright © 2015 Steve Goodrich
