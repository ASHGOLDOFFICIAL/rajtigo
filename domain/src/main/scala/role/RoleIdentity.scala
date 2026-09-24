package org.aulune.rajtigo
package domain
package role


/** Identity of a role.
 *  @param namespace namespace of the owning service.
 *  @param name role name.
 */
final case class RoleIdentity(
    namespace: Namespace,
    name: RoleName,
)
