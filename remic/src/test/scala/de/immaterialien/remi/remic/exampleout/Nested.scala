package de.immaterialien.remi.remic.exampleout

import de.immaterialien.remi.binding._


/**
 * 
 * expected example output for a page with login, pass and a list of currently logged in users 
 * 
 * 
 * @author ulf
 *
 */
trait Nested {
  def name(leaf:Leaf) 
  def pass(leaf:Leaf)
  
  trait LoggedIn {
    def name(leaf:Leaf)
  }
  def online(node:Node[LoggedIn])

}