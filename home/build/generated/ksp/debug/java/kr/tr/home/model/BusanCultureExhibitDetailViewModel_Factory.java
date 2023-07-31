// Generated by Dagger (https://dagger.dev).
package kr.tr.home.model;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;
import kr.tr.domain.repository.BusanCultureExhibitDetailRepository;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class BusanCultureExhibitDetailViewModel_Factory implements Factory<BusanCultureExhibitDetailViewModel> {
  private final Provider<BusanCultureExhibitDetailRepository> busanExhibitDetailProvider;

  public BusanCultureExhibitDetailViewModel_Factory(
      Provider<BusanCultureExhibitDetailRepository> busanExhibitDetailProvider) {
    this.busanExhibitDetailProvider = busanExhibitDetailProvider;
  }

  @Override
  public BusanCultureExhibitDetailViewModel get() {
    return newInstance(busanExhibitDetailProvider.get());
  }

  public static BusanCultureExhibitDetailViewModel_Factory create(
      Provider<BusanCultureExhibitDetailRepository> busanExhibitDetailProvider) {
    return new BusanCultureExhibitDetailViewModel_Factory(busanExhibitDetailProvider);
  }

  public static BusanCultureExhibitDetailViewModel newInstance(
      BusanCultureExhibitDetailRepository busanExhibitDetail) {
    return new BusanCultureExhibitDetailViewModel(busanExhibitDetail);
  }
}
