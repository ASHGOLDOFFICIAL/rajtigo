package org.aulune.rajtigo
package domain

/** Namespace of a permission, used to avoid collisions between services. */
opaque type Namespace <: String = String


object Namespace:
  private val regex = "^[a-z_]+$".r

  /** Returns [[Namespace]] if argument is valid, i.e.:
   *    - Namespace is non-empty.
   *    - Consists only of lower-case latin letters and `_` symbols.
   *  @param namespace permission namespace.
   */
  def apply(namespace: String): Option[Namespace] =
    Option.when(regex.matches(namespace))(namespace)

  /** Unsafe constructor to use inside always-valid boundary.
   *  @param namespace permission namespace.
   *  @throws IllegalArgumentException if given params are invalid.
   */
  def unsafe(namespace: String): Namespace =
    apply(namespace).getOrElse(throw IllegalArgumentException())
