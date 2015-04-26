# {{name}}

Pathmanager

## Prerequisites

This project requires Java JDK 1.7 or greater.
You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

You can get the Windows version of the Leinengen installer her:
    https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein.bat

Install Leinengen by running the script or batch file, whichever applies to you.

## Compiling
For this to run, you need to compile both the Closurescript.
To compile the Clojurescript run

    lein cljsbuild once

You also need to create the database tables. To do this type:

    lien ragtime migrate

## Running

To start a web server for the application, run:

    lein ring server

This compiles the Clojure code and starts a server at http://localhost:3000

## License

Copyright © {{year}} Steve Goodrich
