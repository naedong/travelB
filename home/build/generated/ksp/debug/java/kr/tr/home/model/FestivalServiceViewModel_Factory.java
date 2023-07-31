// Generated by Dagger (https://dagger.dev).
package kr.tr.home.model;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;
import kr.tr.domain.repository.FestivalServiceRepository;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class FestivalServiceViewModel_Factory implements Factory<FestivalServiceViewModel> {
  private final Provider<FestivalServiceRepository> festivalServiceRepositoryProvider;

  public FestivalServiceViewModel_Factory(
      Provider<FestivalServiceRepository> festivalServiceRepositoryProvider) {
    this.festivalServiceRepositoryProvider = festivalServiceRepositoryProvider;
  }

  @Override
  public FestivalServiceViewModel get() {
    return newInstance(festivalServiceRepositoryProvider.get());
  }

  public static FestivalServiceViewModel_Factory create(
      Provider<FestivalServiceRepository> festivalServiceRepositoryProvider) {
    return new FestivalServiceViewModel_Factory(festivalServiceRepositoryProvider);
  }

  public static FestivalServiceViewModel newInstance(
      FestivalServiceRepository festivalServiceRepository) {
    return new FestivalServiceViewModel(festivalServiceRepository);
  }
}