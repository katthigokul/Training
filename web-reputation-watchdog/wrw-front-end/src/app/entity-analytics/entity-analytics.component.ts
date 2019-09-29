import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Chart } from 'chart.js';
import { ActivatedRoute, Router } from '@angular/router';
import { bindCallback } from 'rxjs';

@Component({
  selector: 'app-entity-analytics',
  templateUrl: './entity-analytics.component.html',
  styleUrls: ['./entity-analytics.component.css']
})
export class EntityAnalyticsComponent implements OnInit {

  constructor(
    private DataService: DataService,
    private router: Router,
    private route: ActivatedRoute
  ) { }
  chart = [];
  totalReviews: any;
  genuineReviews = 0;
  isUserLoggedIn: any;
  domain: any;
  emailId: any;
  entityId: string;
  fakeReviews = 0;
  aspect1: string[];
  aspect2: string[];
  aspect3: string[];
  aspect4: string[];
  aspects = null;
  card: any;
  title: string;
  allAspectsRatings = new Map();
  monthWithYear: any;
  dataset = [];
  backgroundColor = ['#C4DBF6', '#6CDAEE', '#1B9CE5', '#0074E1', 'blue', ].reverse();

  ngOnInit() {
    console.log(123);
    this.title = this.route.snapshot.paramMap.get('title');
    this.entityId = this.route.snapshot.paramMap.get('entityId');
    this.emailId = this.route.snapshot.paramMap.get('emailId');
    this.domain = this.route.snapshot.paramMap.get('domain');
    this.isUserLoggedIn = this.route.snapshot.paramMap.get('isUserLoggedIn');
    // this.title = 'Mcdonalds';
    this.getEntity(this.title);
  }

  goBack() {
    this.router.navigate(['view', {title: this.title, domain: this.domain,
      entityId: this.entityId, isUserLoggedIn: this.isUserLoggedIn, emailId: this.emailId}]);
  }

  getEntity(title: string) {
    this.DataService.getReviewDetails(title).subscribe((response) => {
      console.log(`Response : ${response}`);
      if (response) {
        this.card = response;
        this.genuineFakeReviewCounter(this.card);
      }
    }, (err) => {
      console.log(err);
    });
  }

  genuineFakeReviewCounter(inputCard) {
    for (const review of inputCard.reviewDTOList) {
      if (review.genuine) {
        if (this.aspects == null) {
          this.aspects = Object.keys(review.aspectReview);
        }
        this.genuineReviews = this.genuineReviews + 1;
      } else {
        this.fakeReviews = this.fakeReviews + 1;
      }
    }
    this.pieChart();
    if (Object.keys(this.card.reviewDTOList).length > 0) {
      if (Object.keys(this.card.reviewDTOList[0].aspectReview).length > 0) {
        for (const aspect of this.aspects) {
          if (!(aspect === '' || aspect == null || aspect === 'null')) {
            this.ratingCounter(aspect, this.card.reviewDTOList);
          }
        }

        this.barGraph();
      }
    }
  }

  ratingCounter(aspect: string, aspectReviews) {
    let one = 0;
    let two = 0;
    let three = 0;
    let four = 0;
    let five = 0;

    for (const review of aspectReviews) {
      if (review.genuine) {
        const rating = Math.round(Number(review.aspectReview[aspect]));
        switch (rating) {
          case 1:
            one = one + 1;
            break;
          case 2:
            two = two + 1;
            break;
          case 3:
            three = three + 1;
            break;
          case 4:
            four = four + 1;
            break;
          case 5:
            five = five + 1;
            break;
          default:
            console.log('error error');
            break;
        }
      }
    }
    const ratings = [one, two, three, four, five];
    this.allAspectsRatings.set(aspect, ratings);

  }

  pieChart() {
    this.chart = new Chart('pieChart', {
      type: 'pie',
      radius: '100%',
      data: {
        labels: ['Genuine', 'Fake'],
        datasets: [
          {
            backgroundColor: ['#1F6521', '#BC4639'],
            data: [this.genuineReviews, this.fakeReviews],
            borderColor: ['white']
          }
        ]
      },
      options: {
        legend: {
          display: true,
          labels: {
            usePointStyle: true,
          }
        }
      }
    });
  }

  barGraph() {
    this.dataSetter();
    this.chart = new Chart('bar-graph', {
      type: 'bar',
      data: {
        datasets: this.dataset,
        backgroundColor: ['blue'],
        labels: ['1', '2', '3', '4', '5']
      },
      options: {
        legend: {
          display: true,
          labels: {
            usePointStyle: true,
          }
        },
        scales: {
          xAxes: [{
            scaleLabel: {
              display: true,
              labelString: 'Rating',
              fontColor: 'black'
            },
            ticks: {
              fontColor: 'black'
            },
            maxBarThickness: 20,
            gridLines: {
              offsetGridLines: true
            }
          }],
          yAxes: [{
            scaleLabel: {
              display: true,
              labelString: 'Number of reviews',
              fontColor: 'black'
            },
            ticks: {
              fontColor: 'black'
            }
          }]
        }
      }
    });
  }

  dataSetter() {
    for (const aspect of this.aspects) {
      const color = [];
      const currentColor = this.backgroundColor.pop();
      for (let i = 1; i <= 5; i++ ) {
        color.push(currentColor);
      }
      const data = {label: aspect, data: this.allAspectsRatings.get(aspect), backgroundColor: color};
      console.log(data);
      this.dataset.push(data);
    }
  }


  lineGraph(wantedMonths: string[], finalRatings: any[]) {
    const positive: number = finalRatings[0];
    const negative: number = finalRatings[1];
    const neutral: number = finalRatings[2];
    this.chart = new Chart('line-graph', {
      type: 'line',
      data: {
        datasets: [
          {
            data: positive,
            // data: [18, 6, 6, 3, 0, 4],
            borderColor: '#53900F',
            fill: true,
            // backgroundColor: 'green',
            label: '+ive Reviews'
          },
          {
            data: negative,
            borderColor: '#E64398',
            // backgroundColor: 'yellow',
            fill: true,
            label: '-ive Reviews'
          },
          {
            data: neutral,
            borderColor: '#77A6F7',
            fill: true,
            label: 'Neutral Reviews'
          }
        ],
        labels: wantedMonths
      },
      options: {
        responsive: true,
        legend: {
          labels: {
            usePointStyle: true,
            backgroundColor: 'red'
          },
          display: true
        },
        scales: {
          xAxes: [{
            display: true
          }],
          yAxes: [{
            display: true
          }],
        }
      }
    });
  }

  monthAndYearSetter() {
    const splitted = String(this.monthWithYear).split('-');
    const year = Number(splitted[0]);
    const month = Number(splitted[1]);
    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    const selectedMonths = [];
    const requiredPattern = [];
    if (month > 7) {
      const remainingMonths = 6 - (months.length - (month - 1));
      let i = 0;
      for (i = month - 1; i < months.length; i++) {
        selectedMonths.push(months[i]);
        requiredPattern.push((String (year) + '-' + (i + 1)));
      }
      for (i = 0; i < remainingMonths; i++) {
        selectedMonths.push(months[i]);
        requiredPattern.push((String (year + 1) + '-' + (i + 1)));
      }
    } else {
      let i = 0;
      for (i = month - 1; i < month + 5; i++) {
        selectedMonths.push(months[i]);
        requiredPattern.push((String (year) + '-' + (i + 1)));
      }
    }

    this.lineGraphDataSetter(requiredPattern, selectedMonths);

  }

  lineGraphDataSetter(patterns: string[], wantedMonths: string[]) {
    const allRatingsOfAllPattern = [];
    const allPostiveRatings = [];
    const allNegativeRatings = [];
    const allNeutralRatings = [];
    let finalRatings = [];
    for (const pattern of patterns) {
      let positiveReviews = 0;
      let negativeReviews = 0;
      let neutralReviews = 0;
      let allRatingsOfPattern = [];
      for (const review of this.card.reviewDTOList) {
        if (review.genuine) {
          const year = Number (review.reviewedOn.slice(0, 4) );
          const month = Number (review.reviewedOn.slice(5, 7) );
          const reviewPattern = String (year + '-' + month);
          if (pattern == reviewPattern) {
            let total = 0;
            for (const aspect of Object.keys(review.aspectReview)) {
              total = total + Number (review.aspectReview[aspect]);
            }
            if ( (total / Object.keys(review.aspectReview).length) >= 3) {
              positiveReviews = positiveReviews + 1;
            } else {
              if ( (total / Object.keys(review.aspectReview).length) <= 2) {
                negativeReviews = negativeReviews + 1;
              } else {
                neutralReviews = neutralReviews + 1;
              }
            }
            // const overAllRating = review.entityReview
            console.log(34);
          } else {
            console.log(54);
          }
        // console.log(Number (review.reviewedOn.slice(0, 7)) );
        }
      }

      allRatingsOfPattern = [positiveReviews, negativeReviews, neutralReviews];
      allRatingsOfAllPattern.push(allRatingsOfPattern);
    }

    for (const patternRatings of allRatingsOfAllPattern) {
      allPostiveRatings.push(patternRatings[0]);
      allNegativeRatings.push(patternRatings[1]);
      allNeutralRatings.push(patternRatings[2]);
    }
    finalRatings = [allPostiveRatings, allNegativeRatings, allNeutralRatings];
    this.lineGraph(wantedMonths, finalRatings);

  }
}
