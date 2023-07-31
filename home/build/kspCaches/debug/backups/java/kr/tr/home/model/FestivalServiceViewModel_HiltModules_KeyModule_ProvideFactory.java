// Generated by Dagger (https://dagger.dev).
package kr.tr.home.model;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.internal.lifecycle.HiltViewModelMap.KeySet")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class FestivalServiceViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
  @Override
  public String get() {
    return provide();
  }

  public static FestivalServiceViewModel_HiltModules_KeyModule_ProvideFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static String provide() {
    return Preconditions.checkNotNullFromProvides(FestivalServiceViewModel_HiltModules.KeyModule.provide());
  }

  private static final class InstanceHolder {
    private static final FestivalServiceViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new FestivalServiceViewModel_HiltModules_KeyModule_ProvideFactory();
  }
}
