import { Component } from '@angular/core';
import { HousingLocationInfo } from '../housing-location';
import { HousingLocation } from "../housing-location/housing-location";
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [HousingLocation, JsonPipe],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  readonly baseUrl = 'https://angular.dev/assets/images/tutorials/common';
  
  housingLocation: HousingLocationInfo = {
    id: 9999,
    name: 'Test Home',
    city: 'Test city',
    state: 'ST',
    photo: `${this.baseUrl}/example-house.jpg`,
    availableUnits: 99,
    wifi: true,
    laundry: false,
  };
}
