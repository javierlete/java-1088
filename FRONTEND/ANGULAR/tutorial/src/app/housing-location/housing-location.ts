import { Component, input } from '@angular/core';
import { HousingLocationInfo } from '../housing-location';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-housing-location',
  imports: [JsonPipe],
  templateUrl: './housing-location.html',
  styleUrl: './housing-location.css',
})
export class HousingLocation {
  housingLocation = input.required<HousingLocationInfo>();
}
