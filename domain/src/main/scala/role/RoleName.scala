package org.aulune.rajtigo
package domain
package role

/** Name of a role, unique within its namespace. */
opaque type RoleName <: String = String


object RoleName:
  private val regex = "^[a-z_]+$".r

  /** Returns [[RoleName]] if argument is valid, i.e.:
   *    - Name is non-empty.
   *    - Consists only of lower-case latin letters and `_` symbols.
   *  @param name role name.
   */
  def apply(name: String): Option[RoleName] =
    Option.when(regex.matches(name))(name)

  /** Unsafe constructor to use inside always-valid boundary.
   *  @param name role name.
   *  @throws IllegalArgumentException if given params are invalid.
   */
  def unsafe(name: String): RoleName =
    apply(name).getOrElse(throw IllegalArgumentException())
