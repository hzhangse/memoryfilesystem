package com.github.marschall.memoryfilesystem;

import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.Collections;
import java.util.Set;

import com.github.marschall.memoryfilesystem.MemoryUserPrincipalLookupService.MemoryGroup;
import com.github.marschall.memoryfilesystem.MemoryUserPrincipalLookupService.MemoryUser;

final class EntryCreationContext {

  final Set<Class<? extends FileAttributeView>> additionalViews;
  final Set<PosixFilePermission> perms;
  final UserPrincipal user;
  final GroupPrincipal group;
  final MemoryFileSystem fileSystem;

  EntryCreationContext(Set<Class<? extends FileAttributeView>> additionalViews,
          Set<PosixFilePermission> perms, UserPrincipal user,
          GroupPrincipal group, MemoryFileSystem fileSystem) {
    this.additionalViews = additionalViews;
    this.perms = perms;
    this.user = user;
    this.group = group;
    this.fileSystem = fileSystem;
  }

  Class<? extends FileAttributeView> firstView() {
    return this.additionalViews.iterator().next();
  }

  static EntryCreationContext empty() {
    return new EntryCreationContext(Collections.<Class<? extends FileAttributeView>>emptySet(),
            Collections.<PosixFilePermission>emptySet(), new MemoryUser("dummy"), new MemoryGroup("dummy"), null);
  }

}
