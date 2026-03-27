import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { HousingService } from '../housing';
import { HousingLocationInfo } from '../housing-location';
import { HousingLocation } from "../housing-location/housing-location";

@Component({
  selector: 'app-home',
  imports: [HousingLocation],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  housingLocationList: HousingLocationInfo[] = [];
  housingService: HousingService = inject(HousingService);

  filteredLocationList: HousingLocationInfo[] = [];
  changeDetectorRef: ChangeDetectorRef = inject(ChangeDetectorRef);

  constructor() {
    this.housingService.getAllHousingLocations().then((housingLocationList: HousingLocationInfo[]) => { // NOSONAR
      this.housingLocationList = housingLocationList;
      this.filteredLocationList = housingLocationList;
      this.changeDetectorRef.markForCheck();
    });
  }

  filterResults(text: string) {
    if (!text) {
      this.filteredLocationList = this.housingLocationList;
      return;
    }
    this.filteredLocationList = this.housingLocationList.filter((housingLocation) =>
      housingLocation?.city.toLowerCase().includes(text.toLowerCase()),
    );
  }
}
