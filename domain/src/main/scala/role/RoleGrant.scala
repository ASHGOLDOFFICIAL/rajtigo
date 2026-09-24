package org.aulune.rajtigo
package domain
package role

import resource.Resource


/** Grant of a role to a user, optionally scoped to a resource.
 *  @param user user the role is granted to.
 *  @param role identity of the granted role.
 *  @param resource resource the grant is scoped to. Must be `None` if the role
 *    is global.
 */
final case class RoleGrant private (
    user: UserId,
    role: RoleIdentity,
    resource: Option[Resource],
)


object RoleGrant:
  /** Returns [[RoleGrant]] if the resource scoping is consistent with the given
   *  role's applicable resource types.
   *  @param user user the role is granted to.
   *  @param role the role being granted.
   *  @param resource resource the grant is scoped to, `None` for a global
   *    grant.
   */
  def apply(
      user: UserId,
      role: Role,
      resource: Option[Resource],
  ): Option[RoleGrant] =
    val isConsistent = resource match
      case None      => role.resourceTypes.isEmpty
      case Some(ref) => role.resourceTypes.contains(ref.resourceType)
    Option.when(isConsistent)(
      new RoleGrant(user = user, role = role.identity, resource = resource),
    )

  /** Unsafe constructor to use inside always-valid boundary.
   *  @param user user the role is granted to.
   *  @param role the role being granted.
   *  @param resource resource the grant is scoped to, `None` for a global
   *    grant.
   *  @throws IllegalArgumentException if the resource scoping is inconsistent
   *    with the role's applicable resource types.
   */
  def unsafe(
      user: UserId,
      role: Role,
      resource: Option[Resource],
  ): RoleGrant = apply(
    user = user,
    role = role,
    resource = resource,
  ).getOrElse(throw IllegalArgumentException())
