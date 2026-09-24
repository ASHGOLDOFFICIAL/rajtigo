package org.aulune.rajtigo
package domain

import scala.util.Try

/** Unique identifier of a user. */
opaque type UserId <: String = String


object UserId:
  /** Returns a [[UserId]] from the given string. */
  def apply(id: String): UserId = id
