import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Hero } from '../hero';
import { HeroService } from '../hero.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-heroes',
  standalone: false,
  templateUrl: './heroes.component.html',
  styleUrl: './heroes.component.css',
})
export class HeroesComponent implements OnInit {
  heroes: Hero[] = [];

  constructor(private readonly heroService: HeroService, private readonly messageService: MessageService, private readonly changeDetectorRef: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.getHeroes();
  }

  getHeroes(): void {
    this.heroService.getHeroes()
      .subscribe(heroes => {
        this.heroes = heroes;
        this.changeDetectorRef.markForCheck();
      });
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.heroService.addHero({ name } as Hero)
      .subscribe(hero => {
        this.heroes.push(hero);
        this.changeDetectorRef.markForCheck();
      });
  }

  delete(hero: Hero): void {
    if (!confirm(`Are you sure that you want to delete ${hero.name}?`)) {
      return;
    }

    this.heroService.deleteHero(hero.id).subscribe(() => {
      this.heroes = this.heroes.filter(h => h !== hero);
      this.changeDetectorRef.markForCheck();
    });
  }
}
