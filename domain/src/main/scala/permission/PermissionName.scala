package org.aulune.rajtigo
package domain
package permission

/** Name of a permission, unique within its namespace. */
opaque type PermissionName <: String = String


object PermissionName:
  private val regex = "^[A-Za-z_-]+$".r

  /** Returns [[PermissionName]] if argument is valid, i.e.:
   *    - Name is non-empty.
   *    - Consists only of latin letters and `_` and `-` symbols.
   *  @param name permission name.
   */
  def apply(name: String): Option[PermissionName] =
    Option.when(regex.matches(name))(name)

  /** Unsafe constructor to use inside always-valid boundary.
   *  @param name permission name.
   *  @throws IllegalArgumentException if given params are invalid.
   */
  def unsafe(name: String): PermissionName =
    apply(name).getOrElse(throw IllegalArgumentException())
