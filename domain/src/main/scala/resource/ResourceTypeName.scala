package org.aulune.rajtigo
package domain
package resource

/** Name of a resource type, unique within its namespace. */
opaque type ResourceTypeName <: String = String


object ResourceTypeName:
  private val regex = "^[A-Za-z_-]+$".r

  /** Returns [[ResourceTypeName]] if argument is valid, i.e.:
   *    - Name is non-empty.
   *    - Consists only of latin letters and `_` and `-` symbols.
   *  @param name resource type name.
   */
  def apply(name: String): Option[ResourceTypeName] =
    Option.when(regex.matches(name))(name)

  /** Unsafe constructor to use inside always-valid boundary.
   *  @param name resource type name.
   *  @throws IllegalArgumentException if given params are invalid.
   */
  def unsafe(name: String): ResourceTypeName =
    apply(name).getOrElse(throw IllegalArgumentException())
