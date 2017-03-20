# A frontend to miXr written in Elm

[Elm](http://elm-lang.org/) is a pretty cool language, and I'm using this as an opportunity to play around with it.

The source code is all in mixr.elm.  Transpile it by using `elm-make mixr.elm --output mixr.js`.
mixr.html then pulls in our code (in mixr.js) along with Bootstrap 4 from CDNs to make it look somewhat presentable.  
To actually view the code in action, you will want to run the backend and serve the frontend assets from port 8000.
You can use elm-reactor to host the stuff, but be sure to view `mixr.html` if you want the CSS to be pulled in correctly.