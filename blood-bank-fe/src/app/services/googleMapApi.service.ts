import { Injectable } from '@angular/core';
import {Loader} from "@googlemaps/js-api-loader";
const GOOGLE_MAPS_API_KEY = 'AIzaSyCRireAseTUQ65GrN5xRvAWzkqRHrl83eE';
export type Maps = typeof google.maps;

@Injectable()
export class GoogleMapApiService {

  public readonly googleApi = this.load();

  private load(): Promise<typeof google> {
    const loader = new Loader(
      {
        apiKey:GOOGLE_MAPS_API_KEY,
        id: "__googleMapsScriptId",
        version: "weekly",
        libraries: ["places"]
      }
    )
    return loader.load();
  }

}
