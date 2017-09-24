# gadgets
A toolkit for web development, which enhances Scalatags and unifies it with Scala.Rx

There are a million different options for doing Web development in Scala.js, including frameworks ranging from React to Suzaku to Bindings to Udash. All of them are lovely, and if you find one that suits you, use it.

However, a lot of folks decide to work at a lower level: in particular, they find that they like @lihaoyi's Scalatags library, and want to build their site using that. Then they discover Scala.Rx, and decide that they want to use that. Then they try to combine the two, and find that it isn't quite that easy.

[Querki](https://www.querki.net) is one of the oldest shipping products built on Scala.js; its client predates pretty much all of the major frameworks. So I wound up in just that position, building something myself out of Scalatags and Scala.Rx, and puzzling out how to use them together to produce a reasonably serious FRP framework. This library is the result.

## Using Gadgets in Your Application

To use Gadgets in your Scala.js project, include it as usual:
```
libraryDependencies += "org.querki" %%% "gadgets" % "0.2"
```

Gadgets has transitive dependencies on [Scalatags](https://index.scala-lang.org/lihaoyi/scalatags) and [Scala.Rx](https://index.scala-lang.org/lihaoyi/scala.rx), as well as [sQuery](https://index.scala-lang.org/jducoeur/squery) and of course the [DOM facade](https://index.scala-lang.org/scala-js/scala-js-dom). All of these will be pulled in automatically.

Gadgets *currently* requires the use of jQuery, so you will need to include that in your top-level HTML files. The [jquery-facade](https://index.scala-lang.org/jducoeur/jquery-facade) is pulled in transitively. I plan to phase out the use of jQuery eventually, in favor of sQuery, but that's a long-term project.

## About Gadgets

The Gadgets toolkit is mainly focused on extending the core notion of Scalatags -- being able to build HTML nodes using simple Scala functions -- and enabling you to build reusable higher-level Gadgets that fit into those functions naturally. It also provides helpers for some of the common problems you encounter when building complex UIs.

Gadgets is specifically *not* pure-functional in the way some frameworks are: it doesn't use a VDOM, and admits that it is closely connected to the actual DOM. State is principally managed with Scala.Rx `Var`s, in a slightly old-fashioned data-binding model. In return, it is a bit "closer to the metal". Personally, I find it slightly easier to understand how the code relates to what shows up on-screen, but it's very much a matter of taste.

This library is just a toolkit, not a full-fledged framework. There *is* a full, opinionated framework in Querki, built on top of this, and I might at some point refactor that out and make it available as a separate library. But the Gadgets are quite useful on their own, whether or not you are incorporating them into a framework.

## A Motivating Example

Here's an example of a hypothetical Gadget -- in this case, a pane composed of a date range, and a section that displays the results of a server query for that date range. It's not fully-fleshed out (and as of this writing might have bugs), but illustrates what a typical mid-level Gadget looks like. Note that this is just an illustration of a partial program -- a full working example would require several other Gadgets, such as TransactionList.
```
import org.scalajs.dom
import org.querki.gadgets._
import rx._
import scalatags.JsDom.all._

class TransactionRangeGadget(implicit ctx:Ctx.Owner) extends Gadget[dom.html.Div] {
  val startDate = Var[Date](Date.now)
  val endDate = Var[Date](Date.now)
  val datePair = Rx { (startDate(), endDate()) }
  
  datePair.triggerLater {
    transactionsPane.foreach { tlist => tlist.updateWithDates(datePair.now) }
  }
  
  val transactionsPane = GadgetRef[TransactionList]
    .whenRendered { tlist =>
      tlist.updateWithDates(datePair.now)
    }

  def doRender() =
    div(
      p("Please specify the date range to show:"),
      span(
        new DatePickerGadget(startDate),
        " through ",
        new DatePickerGadget(endDate)
      ),
      
      transactionsPane <= new TransactionList()
    )
}
```
That doesn't show everything (and isn't the only way you might organize this problem), but it illustrates some of the most useful bits:

* You can freely mix Gadgets with HTML nodes in your Scalatags
* Gadgets can and frequently do manage data-binding with Scala.Rx
* You can define class members for particular nodes in the Scalatags tree, and access them elsewhere (in this case, the `transactionsPane`) -- this makes it much easier to build complex, inter-related UIs

## Building a Gadget

### TODO

* Choose a richer example: start simple, and build it up in the later sections
* Relationship of Gadgets to Elements
* Gadgets are (essentially) live Scala code attached to the DOM
* ManagedFrag, and the key difference between Gadgets and Scalatags: Gadgets actually maintain the relationship to the DOM
* The meaning of "render" in ManagedFrag
* The onCreate() and onRendered() hooks
* Accessing the tree via parentOptRx
* GadgetLookup, and finding Gadgets from the DOM elements
* Some of the above probably belongs in an "Advanced Concepts" section

## GadgetRef

### TODO

* The point of GadgetRef: to let you build a readable HTML tree, and easily refer to Gadgets inside it
* A holder to a reference to a Gadget in the tree
* The all-important opt member
* map, the iffily-named flatMap, and foreach
* mapOrElse, mapElem
* isDefined and isEmpty
* assignment: the <= and <~ operators
* whenRendered (and maybe whenSet, although make clear that that's usually not right)
* Gadget.of for hooking plain HTML nodes

## Using Scala.Rx in Gadgets

### TODO

* Proper use of Rx vs Var as parameters -- consume vs produce
* Talk about `Ctx.Owner`, and how to use it -- thread it down from the top.
* RxAttr, to let you use Rx'es as attribute values
* RxEmptyable, once we spruce that typeclass up and promote it into the library
* Eventually, docs for the various common Rx utility types

## Version History

* **0.3** -- Significant changes to the functions exposed by `GadgetRef`, because the signatures of `map()` and `flatMap()` were kind of scungy, and didn't do what you typically want with the current version of Scala.Rx. The old functions still exists as `mapNow()` and `flatMapNow()`, to signify their "now-ness", but there are also `mapRx()` and `flatMapRx()`, which produce proper Rx's and work as you would expect. Also added `mapRxOrElse()` and `flatMapRxOrElse()` as boilerplate-killers. 
* **0.2** -- Added the RxDiv and RxTextFrag components, and the RxEmptyable typeclass.
* **0.1** -- Initial release. This is called 0.1 because it's incomplete: it only contains the basics, none of the actual Gadgets that we use in Querki yet. But what is here is pretty battle-tested, and has been in use in Querki for a couple of years now.
