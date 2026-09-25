package org.aulune.rajtigo
package domain
package grant


/** Constraints that exist on grants as a collection. */
enum GrantConstraint:
  /** The granted role must be registered. */
  case RoleExists

  /** The resource type the grant is scoped to must be registered. */
  case ResourceTypeExists
