import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { Hero } from '../hero';
import { ActivatedRoute } from '@angular/router';
import { HeroService } from '../hero.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-hero-detail',
  standalone: false,
  templateUrl: './hero-detail.component.html',
  styleUrl: './hero-detail.component.css',
})
export class HeroDetailComponent implements OnInit {

  @Input() hero?: Hero;

  constructor(private readonly changeDetectorRef: ChangeDetectorRef, private readonly heroService: HeroService, private readonly route: ActivatedRoute, private readonly location: Location) { }

  ngOnInit(): void {
    this.getHero();
  }

  getHero(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.heroService.getHero(id)
      .subscribe(hero => {
        this.hero = hero;
        this.changeDetectorRef.markForCheck();
      });
  }

  save(): void {
    if (this.hero) {
      this.heroService.updateHero(this.hero)
        .subscribe(() => this.goBack());
    }
  }
  
  goBack(): void {
    this.location.back();
  }
}
