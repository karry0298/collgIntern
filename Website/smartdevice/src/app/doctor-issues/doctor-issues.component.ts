import { Component, OnInit ,Input} from '@angular/core';
import { Patient } from '../patient';
@Component({
  selector: 'app-doctor-issues',
  templateUrl: './doctor-issues.component.html',
  styleUrls: ['./doctor-issues.component.scss']
})
export class DoctorIssuesComponent implements OnInit {
  @Input() patient: Patient;
  constructor() { }

  ngOnInit() {
  }

}
