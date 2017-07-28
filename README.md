# gadgets
A toolkit for web development, built on top of Scalatags and Scala.Rx

There are a million different options for doing Web development in Scala.js, including frameworks ranging from React to Suzaku to Bindings to Udash. All of them are lovely, and if you find one that suits you, use it.

However, a lot of folks decide to work at a lower level: in particular, they find that they like @lihaoyi's Scalatags library, and want to build their site using that. Then they discover Scala.Rx, and decide that they want to use that. Then they try to combine the two, and find that it isn't quite that easy.

[Querki](https://www.querki.net) is one of the oldest shipping products built on Scala.js; its client predates pretty much all of the major frameworks. So I wound up in just that position, building something myself out of Scalatags and Scala.Rx, and puzzling out how to use them together to produce a reasonably serious FRP framework. This library is the result.

### Using Gadgets in Your Application

To use Gadgets in your Scala.js project, include it as usual:
```
libraryDependencies += "org.querki" %%% "gadgets" % "0.1"
```

Gadgets has transitive dependencies on [Scalatags](https://index.scala-lang.org/lihaoyi/scalatags) and [Scala.Rx](https://index.scala-lang.org/lihaoyi/scala.rx), as well as [sQuery](https://index.scala-lang.org/jducoeur/squery) and of course the [DOM facade](https://index.scala-lang.org/scala-js/scala-js-dom). All of these will be pulled in automatically.

Gadgets *currently* requires the use of jQuery, so you will need to include that in your top-level HTML files. The [jquery-facade](https://index.scala-lang.org/jducoeur/jquery-facade) is pulled in transitively. I plan to phase out the use of jQuery eventually, in favor of sQuery, but that's a long-term project.

### About Gadgets

The Gadgets toolkit is mainly focused on extending the core notion of Scalatags -- being able to build HTML nodes using simple Scala functions -- and enabling you to build reusable higher-level Gadgets that fit into those functions naturally. Along the way, it provides tools for addressing specific nodes in your HTML graph easily (using the `GadgetRef` wrapper), and makes it easier to hook behavior into those Gadgets.

Gadgets is specifically *not* pure-functional in the way some frameworks are: it doesn't use a VDOM, and admits that it is closely related to the actual DOM. State is principally managed with Scala.Rx `Var`s, in a slightly old-fashioned data-binding model. In return, it is a bit "closer to the metal". Personally, I find it slight easier to understand how the code relates to what shows up on-screen, but it's very much a matter of taste.

This library is just a toolkit, not a full-fledged framework. There *is* a full, opinionated framework in Querki, built on top of this, and I might at some point refactor that out and make it available as a separate library.

### Building a Gadget

### GadgetRef

### Using Scala.Rx in Gadgets

### Version History

* **0.1** -- Initial release. This is called 0.1 because it's incomplete: it only contains the basics, none of the actual Gadgets that we use in Querki yet. But what is here is pretty battle-tested, and has been in use in Querki for a couple of years now.
