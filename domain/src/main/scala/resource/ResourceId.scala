package org.aulune.rajtigo
package domain
package resource

/** Identifier of a concrete resource instance. */
opaque type ResourceId <: String = String


object ResourceId:
  private val regex = "^[A-Za-z_-]+$".r

  /** Returns [[ResourceId]] if argument is valid, i.e. not empty and not
   *  whitespace only.
   *  @param id resource id.
   */
  def apply(id: String): Option[ResourceId] = Option.when(regex.matches(id))(id)

  /** Unsafe constructor to use inside always-valid boundary.
   *  @param id resource id.
   *  @throws IllegalArgumentException if given params are invalid.
   */
  def unsafe(id: String): ResourceId =
    apply(id).getOrElse(throw IllegalArgumentException())
