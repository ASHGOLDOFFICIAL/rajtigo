package org.aulune.rajtigo
package domain
package permission


/** Identity of a permission.
 *  @param namespace permission namespace.
 *  @param name permission name.
 */
final case class PermissionIdentity(
    namespace: Namespace,
    name: PermissionName,
)
