package org.aulune.rajtigo.domain

/** Human-readable description of a permission. */
opaque type Description <: String = String


object Description:
  /** Returns [[Description]] if argument is valid.
   *
   *  To be valid a string should not be empty and should not consist of
   *  whitespace only. Whitespace is stripped.
   *
   *  @param description permission description.
   */
  def apply(description: String): Option[Description] =
    val stripped = description.strip()
    Option.when(stripped.nonEmpty)(stripped)

  /** Unsafe constructor to use inside always-valid boundary.
   *  @param description permission description.
   *  @throws IllegalArgumentException if given params are invalid.
   */
  def unsafe(description: String): Description =
    apply(description).getOrElse(throw IllegalArgumentException())
