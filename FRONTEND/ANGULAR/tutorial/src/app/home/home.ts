import { Component, inject } from '@angular/core';
import { HousingLocationInfo } from '../housing-location';
import { HousingLocation } from "../housing-location/housing-location";
import { JsonPipe } from '@angular/common';
import { HousingService } from '../housing';

@Component({
  selector: 'app-home',
  imports: [HousingLocation, JsonPipe],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  housingLocationList: HousingLocationInfo[] = [];
  housingService: HousingService = inject(HousingService);
  
  constructor() {
    this.housingLocationList = this.housingService.getAllHousingLocations();
  }
}
