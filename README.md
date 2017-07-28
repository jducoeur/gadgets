# gadgets
A toolkit for web development, built on top of Scalatags and Scala.Rx

There are a million different options for doing Web development in Scala.js, including frameworks ranging from React to Suzaku to Bindings to Udash. All of them are lovely, and if you find one that suits you, use it.

However, a lot of folks decide to work at a lower level: in particular, they find that they like @lihaoyi's Scalatags library, and want to build their site using that. Then they discover Scala.Rx, and decide that they want to use that. Then they try to combine the two, and find that it isn't quite that easy.

[Querki](https://www.querki.net) is one of the oldest shipping products built on Scala.js; its client predates pretty much all of the major frameworks. So I wound up in just that position, building something myself out of Scalatags and Scala.Rx, and puzzling out how to use them together to produce a reasonably serious FRP framework. This library is the result.

### Using Gadgets

To use Gadgets in your Scala.js project, include it as usual:
```
libraryDependencies += "org.querki" %%% "gadgets" % "0.1"
```

Gadgets has transitive dependencies on [Scalatags](https://index.scala-lang.org/lihaoyi/scalatags) and [Scala.Rx](https://index.scala-lang.org/lihaoyi/scala.rx), as well as [sQuery](https://index.scala-lang.org/jducoeur/squery) and of course the [DOM facade](https://index.scala-lang.org/scala-js/scala-js-dom). All of these will be pulled in automatically.

Gadgets *currently* requires the use of jQuery, so you will need to include that in your top-level HTML files. The [jquery-facade](https://index.scala-lang.org/jducoeur/jquery-facade) is pulled in transitively. I plan to phase out the use of jQuery eventually, in favor of sQuery, but that's a long-term project.

